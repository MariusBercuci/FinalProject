import { Product } from '../../../shared/models/product';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { ActivatedRoute, RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products:Product[] = [];

  constructor(private productService:ProductService, activatedRoute:ActivatedRoute) {
    activatedRoute.params.subscribe((params) =>{
      if(params.searchTerm)
      this.products = this.productService.getAllProductsBySearchTerm(params.searchTerm);
      else if(params.tag)
      this.products = this.productService.getAllProductsByTag(params.tag);
      else
      this.products = productService.getAll();

    })
    
   }

  ngOnInit(): void {
  }

}
