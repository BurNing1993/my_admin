const TOKEN_KEY = '_token';

export function getToken() {
  return localStorage[TOKEN_KEY];
}

export function setToken(token: string) {
  localStorage[TOKEN_KEY] = token;
}

export function removeToken() {
  localStorage.removeItem(TOKEN_KEY);
}
