import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { USER_LOGIN_URL } from '../shared/models/constants/urls';
import { IUserLogin } from '../shared/models/interfaces/IUserLogin';
import { User } from '../shared/models/User';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';

const USER_KEY = 'User';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  [x: string]: any;

  user!: User;
  private apiServerURL = environment.apiUrl;

  private userSubject = 
  new BehaviorSubject<User>(this.getUserFromLocalStorage());
  public userObservable:Observable<User>;
  toastrService: any;
  constructor(private http:HttpClient) {
    this.userObservable = this.userSubject.asObservable();
  }

  public get currentUser():User{
    return this.userSubject.value;
  }

  login(userLogin:IUserLogin):Observable<User>{
    return this.http.post<User>(USER_LOGIN_URL, userLogin).pipe(
      tap({
        next: (user) =>{
          this.setUserToLocalStorage(user);
          this.userSubject.next(user);

        },
        error: (errorResponse) => {
          this.error(errorResponse.error, 'Login Failed');
        }
      })
    );
    
    return this.http.post<User>(`${this.apiServerURL}/users/login`, userLogin);
  }

  saveUser(backendUser:User) {
    this.user = backendUser;
    //throw new Error('Method not implemented.');
  }

  private setUserToLocalStorage(user:User){
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  private getUserFromLocalStorage():User{
    const userJson = localStorage.getItem(USER_KEY);
    if(userJson) return JSON.parse(userJson) as User;
    return new User();
  }

}
