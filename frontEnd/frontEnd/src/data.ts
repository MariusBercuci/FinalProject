import {Product} from './app/shared/models/product';
import { Tag } from './app/shared/models/tag';

export const sample_products: Product[] = [
    {
        id:'1',
        name: 'Samsung S22',
        color: 'Black',
        price: 1200,
        imageUrl: 'assets/samsung s22-1.jpg',
        tags: ['S_series'],
      },

      {
        id:'2',
        name: 'Iphone 13 Pro',
        color: 'Blue',
        price: 1300,
        imageUrl: 'assets/Iphone13-2.jpg',
        tags: ['Iphone'],
      },

      {
        id:'3',
        name: 'Samsung A33',
        color: 'Red',
        price: 400,
        imageUrl: 'assets/samsungA33-3.jpg',
        tags: ['S_series'],
      },

      {
        id:'4',
        name: 'Iphone 13',
        color: 'Red',
        price: 1100,
        imageUrl: 'assets/Iphone13-4.jpg',
        tags: ['Iphone'],
      },

      {
        id:'5',
        name: 'Motorola',
        color: 'Black',
        price: 1100,
        imageUrl: 'assets/motorola-5.jpg',
        tags: ['MotoX'],
      },

      {
        id:'6',
        name: 'Huawei',
        color: 'Blue',
        price: 1900,
        imageUrl: 'assets/huawei-6.jpg',
        tags: ['Hmate'],
      },

      {
        id:'7',
        name: 'Sony',
        color: 'Black',
        price: 2200,
        imageUrl: 'assets/xperia-7.jpg',
        tags: ['Xperia'],
      },

      {
        id:'8',
        name: 'Google Pixel',
        color: 'Black',
        price: 3450,
        imageUrl: 'assets/google-8.jpg',
        tags: ['Pixel'],
      }
]

export const sample_tags:Tag[] = [
  { name: 'All', count: 8 },
  { name: 'S_series', count: 2 },
  { name: 'Iphone', count: 2 },
  { name: 'MotoX', count: 1 },
  { name: 'Pixel', count: 1 },
  { name: 'Hmate', count: 1 },
  { name: 'Xperia', count: 1 },
  
]