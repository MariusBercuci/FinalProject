import { sample_tags } from './../../data';
import { Injectable } from '@angular/core';
import { sample_products } from 'src/data';
import { Product } from '../shared/models/product';
import { Tag } from '../shared/models/tag';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
 
   constructor() { }

  //in the future this need to be connected to the backend
  getAll():Product[]{
    return sample_products;
  }

  getAllProductsBySearchTerm(searchTerm:string) {
    return this.getAll().filter(product => product.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }

  getAllTags():Tag[]{
    return sample_tags;
  }

  getAllProductsByTag(tag:string):Product[]{
    return tag === "All"?
    this.getAll():
    this.getAll().filter(product => product.tags?.includes(tag));
  }

  getProductById(productId:string):Product{
    return this.getAll().find(product => product.id == productId) ?? new Product();
  }


}
