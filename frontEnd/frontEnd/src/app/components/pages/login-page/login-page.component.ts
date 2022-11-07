import { UserService } from 'src/app/service/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/shared/models/User';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm!:FormGroup;
  isSubmitted = false;
  returnUrl = '';
  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private router:Router,
    private http:HttpClient) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email:['', [Validators.required,Validators.email]],
      password:['', Validators.required]
    });

    this.returnUrl = this.activatedRoute.snapshot.queryParams.returnUrl;

  }

  get fc(){
    return this.loginForm.controls;
  }

  submit(){
    this.userService.login(this.loginForm.value).subscribe({
      next: (backendUser: User) =>{
        this.userService.saveUser(backendUser);
        this.router.navigateByUrl(this.returnUrl);
      },
      error: (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    })


    this.isSubmitted = true;
    if(this.loginForm.invalid) return;

    alert(`email: ${this.fc.email.value},
           password: ${this.fc.password.value}`)
  }

}
