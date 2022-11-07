import { environment } from "src/environments/environment";

const BASE_URL = environment.production? '' : 'http://localhost:5000';

export const PRODUCTS_URL = BASE_URL + '/api/products';
export const PRODUCTS_TAGS_URL = PRODUCTS_URL + '/tags';
export const PRODUCTS_BY_SEARCH_URL = PRODUCTS_URL + '/search/';
export const PRODUCTS_BY_TAG_URL = PRODUCTS_URL + '/tag/';
export const PRODUCT_BY_ID_URL = PRODUCTS_URL + '/';


export const USER_LOGIN_URL = '/api/users/login';
export const USER_REGISTER_URL = 'api/users/register';



