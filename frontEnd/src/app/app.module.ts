import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/partial/header/header.component';
import { HomeComponent } from './components/pages/home/home.component';
import { ActivatedRoute, RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/partial/search/search.component';
import { TagsComponent } from './components/partial/tags/tags.component';
import { FoodPageComponent } from './coomponents/pages/food-page/food-page.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'search/:searchTerm', component:HomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SearchComponent,
    TagsComponent,
    FoodPageComponent

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes,{onSameUrlNavigation:'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
