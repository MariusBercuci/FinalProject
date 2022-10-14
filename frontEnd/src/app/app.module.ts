import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/partial/header/header.component';
import { HomeComponent } from './components/pages/home/home.component';
import { ActivatedRoute, RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/partial/search/search.component';
import { TagsComponent } from './components/partial/tags/tags.component';
import { ProductPageComponent } from './components/pages/product-page/product-page.component';
import { CartPageComponent } from './components/pages/cart-page/cart-page.component';
import { TitleComponent } from './components/partial/title/title.component';


const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'search/:searchTerm', component:HomeComponent},
  {path: 'tag/:tag', component:HomeComponent},
  {path:'product/:id', component:ProductPageComponent},
  {path:'cart-page', component: CartPageComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SearchComponent,
    TagsComponent,
    ProductPageComponent,
    CartPageComponent,
    TitleComponent,


  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes,{onSameUrlNavigation:'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
