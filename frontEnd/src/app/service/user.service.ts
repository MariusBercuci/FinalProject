import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { USER_LOGIN_URL } from '../shared/models/constants/urls';
import { IUserLogin } from '../shared/models/interfaces/IUserLogin';
import { User } from '../shared/models/User';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user!: User;
  private apiServerURL = environment.apiUrl;

  private userSubject = new BehaviorSubject<User>(new User());
  public userObservable:Observable<User>;
  constructor(private http:HttpClient) {
    this.userObservable = this.userSubject.asObservable();
  }

  login(userLogin:IUserLogin):Observable<User>{
    //return this.http.post<User>(`$(this.apiUrl}/USER_LOGIN_URL`, userLogin)
    return this.http.post<User>(`${this.apiServerURL}/users/login`, userLogin);
  }






  saveUser(backendUser:User) {
    this.user = backendUser;
    //throw new Error('Method not implemented.');
  }
}
