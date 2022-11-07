import { ProductService } from './../../../service/product.service';
import { Component, OnInit } from '@angular/core';
import { Tag } from 'src/app/shared/models/tag';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {

  tags?:Tag[];

  constructor(productService:ProductService) {
    this.tags = productService.getAllTags();
  }

  ngOnInit(): void {
  }

}
