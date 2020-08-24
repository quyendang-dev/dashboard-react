import React, { Component } from "react";
import {
  Layout,
  Button,
  Input,
  Row,
  Col,
  Card,
  Statistic,
  Table,
  Form,
} from "antd";
import { withRouter } from "react-router-dom";
import { getProductByConditions } from "../util/APIUtils";
import LoadingIndicator from "../common/LoadingIndicator";
import "./Dashboard.css";

const { Sider, Content } = Layout;

class Dashboard extends Component {
  _isMounted = true;
  constructor(props) {
    super(props);

    this.state = {
      name: {
        value: "",
      },
      code: {
        value: "",
      },
      description: {
        value: "",
      },
      status: {
        value: "",
      },
      type: {
        value: "",
      },
      summaries: [],
      products: [],
      isLoading: false,
      collapsed: false,
    };

    this.loadProductList = this.loadProductList.bind(this);
    this.toggle = this.toggle.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  toggle() {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  }

  loadProductList() {
    let promise;

    promise = getProductByConditions(
      this.state.code.value,
      this.state.name.value,
      this.state.description.value,
      this.state.status.value,
      this.state.type.value
    );

    if (!promise) {
      return;
    }

    this.setState({
      isLoading: true,
    });
    promise
      .then((response) => {
        if (this._isMounted) {
          this.setState({
            products: response.productResponses,
            summaries: response.statisticResponses,
            isLoading: false,
          });
        }
      })
      .catch((error) => {
        if (this._isMounted) {
          this.setState({
            isLoading: false,
          });
        }
      });
  }

  componentDidMount() {
    if (this._isMounted) {
      this.loadProductList(
        this.state.code.value,
        this.state.name.value,
        this.state.description.value,
        this.state.type.value
      );
    }
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  handleSubmit(e) {
    e.preventDefault();
    this.loadProductList();
  }

  handleInputChange(event) {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    this.setState({
      [inputName]: {
        value: inputValue,
      },
    });
  }

  render() {
    const cards = this.state.summaries ? (
      this.state.summaries.map((card, index) => (
        <Col key={index} span={8}>
          <Card>
            <Statistic key title={card.title} value={card.quantity} />
          </Card>
        </Col>
      ))
    ) : (
      <Col span={8}></Col>
    );

    const dataSource = this.state.products;
    const columns = [
      {
        title: "Code",
        dataIndex: "code",
        key: "code",
      },
      {
        title: "Name",
        dataIndex: "name",
        key: "name",
      },
      {
        title: "Description",
        dataIndex: "description",
        key: "description",
      },
      {
        title: "Type",
        dataIndex: "type",
        key: "type",
      },
      {
        title: "Status",
        dataIndex: "status",
        key: "status",
      },
    ];

    const layout = {
      labelCol: { span: 4 },
      wrapperCol: { span: 20 },
    };

    const scroll = {
      y:320
    };
    const title = () => 'List of products';

    return (
      <Layout style={{ width: "100%" }}>
        <Sider
          trigger={null}
          collapsible
          collapsed={this.state.collapsed}
          className="dashboard-sider"
          width={300}
        >
          <Form layout={"horizontal"} onSubmit={this.handleSubmit}>
            <Form.Item>
              <span style={{fontSize:"18"}}>Search Product</span>
              <Button style={{ float: "right" }} onClick={this.toggle}>
                X
              </Button>
            </Form.Item>
            <Form.Item label="Code">
              <Input
                name="code"
                onChange={(e) => this.handleInputChange(e)}
                placeholder="Search by  product code"
              />
            </Form.Item>
            <Form.Item label="Name">
              <Input
                name="name"
                onChange={(e) => this.handleInputChange(e)}
                placeholder="Search by product name"
              />
            </Form.Item>
            <Form.Item label="Description">
              <Input
                name="description"
                onChange={(e) => this.handleInputChange(e)}
                placeholder="Search by description"
              />
            </Form.Item>
            <Form.Item label="Status">
              <Input
                name="status"
                onChange={(e) => this.handleInputChange(e)}
                placeholder="Search by status"
              />
            </Form.Item>
            <Form.Item label="Type">
              <Input
                name="type"
                onChange={(e) => this.handleInputChange(e)}
                placeholder="Search by type"
              />
            </Form.Item>
            <Form.Item
              wrapperCol={{ ...layout.wrapperCol, offset: 8 }}
              style={{ float: "right", paddingRight: 20 }}
            >
              <Button type="primary" htmlType="submit">
                Apply
              </Button>
            </Form.Item>
          </Form>
        </Sider>
        <Content
          style={{ margin: "10px", padding: "10px", border: "1px solid gray" }}
        >
          <Layout className="site-statistic-card">
            <Row gutter={16}>{cards}</Row>
          </Layout>

          <Layout className="product-container">
            <Table 
              size="middle"
              rowKey={(record) => record.id}
              columns={columns}
              dataSource={dataSource}
              scroll={scroll}
              title = {title}
            />
          </Layout>
          {this.state.isLoading ? <LoadingIndicator /> : null}
        </Content>
      </Layout>
    );
  }
}

export default withRouter(Dashboard);
