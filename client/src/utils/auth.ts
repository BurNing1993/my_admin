const TOKEN_KEY = '_token';
const TOKEN_PREFIX = 'Bearer ';

export function getToken() {
  return localStorage[TOKEN_KEY];
}

export function setToken(token: string) {
  localStorage[TOKEN_KEY] = TOKEN_PREFIX + token;
}

export function removeToken() {
  localStorage.removeItem(TOKEN_KEY);
}
