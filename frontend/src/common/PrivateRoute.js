import React from 'react';
import {
    Route,
    Redirect
  } from "react-router-dom";
import { ACCESS_TOKEN } from '../constants';
  
  
const PrivateRoute = ({ component: Component, authenticated, ...rest }) =>{
  let authenticatedToken = localStorage.getItem(ACCESS_TOKEN);
  let isAuthentiated = authenticated || authenticatedToken;
  return (
    <Route
      {...rest}
      render={props =>
        isAuthentiated ? (
          <Component {...rest} {...props} />
        ) : (
          <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location }
            }}
          />
        )
      }
    />
)};
  
export default PrivateRoute