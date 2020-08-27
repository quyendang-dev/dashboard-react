import { ACCESS_TOKEN } from "../constants";

const request = (options) => {
  const headers = new Headers({
    "Content-Type": "application/json",
  });

  const anotherOptions = {
      mode: 'cors',
      cache: "no-cache",
      credentials: "same-origin",
      redirect: "follow",
      referrerPolicy: "no-referrer"
  }
  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    );
  }

  const defaults = { headers: headers };
  options = Object.assign(anotherOptions, defaults, options);
  const url = '/api' + options.url;
  return fetch(url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function login(loginRequest) {
  return request({
    url:  "/auth/signin",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}

export function signup(signupRequest) {
  return request({
    url:  "/auth/signup",
    method: "POST",
    body: JSON.stringify(signupRequest),
  });
}

export function checkUsernameAvailability(username) {
  return request({
    url:  "/user/checkUsernameAvailability?username=" + username,
    method: "GET",
  });
}

export function checkEmailAvailability(email) {
  return request({
    url:  "/user/checkEmailAvailability?email=" + email,
    method: "GET",
  });
}

export function getCurrentUser() {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }

  return request({
    url:  "/user/me",
    method: "GET",
  });
}

export function getProductByConditions(code, name, description, status, type) {
  let path = `code=${code}&name=${name}&description=${description}&status=${status}&type=${type}`;
  return request({
    url:  "/products?" + path,
    method: "GET",
  });
}
