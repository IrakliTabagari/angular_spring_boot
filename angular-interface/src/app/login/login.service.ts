import { Injectable } from '@angular/core';
// import {Http, Headers } from '@angular/http';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable} from 'rxjs';
// import {Observable} from 'rxjs/Observable';
// import 'rxjs/add/operator/map';

import { Session } from '../auth/login/session';
import { User } from '../auth/login/user';
import { UserRole } from '../auth/login/userRole';
import { Role } from '../auth/login/role';
import { Right } from '../auth/login/right';
import { Login } from '../auth/login/login';

import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrl = environment.baseUrl;

  private url = this.baseUrl + '/users';
  
  constructor(//private http: Http,
              public http: HttpClient) {

  }


  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url + '/getAllUser', null);
  }

  getRoles(): Observable<Role[]> {
    this.getSession();
    return this.http.get<Role[]>(this.url + '/getRoles', this.httpOptions);
  }

  getAllRights(): Observable<Right[]> {
    this.getSession();
    return this.http.get<Right[]>(this.url + '/getRights', this.httpOptions);
  }

  updateRole(editedRole: Role): Observable<Role> {
    this.getSession();
    return this.http.post<Role>(this.url + '/updateRole', editedRole, this.httpOptions);
  }

  getUser(id: Number): Observable<User> {
    this.getSession();
    return this.http.get<User>(this.url + '/getUserById/' + id, this.httpOptions);
  }

  addUser(newUser: User): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/addUser', newUser, this.httpOptions);
  }

  addRole(role: Role): Observable<Role>{
    this.getSession();
    return this.http.post<Role>(this.url + '/addRole', role, this.httpOptions)
  }

  addUserRole(userRole: UserRole): Observable<UserRole> {
    this.getSession();
    return this.http.post<UserRole>(this.url + '/addUserRole', userRole, this.httpOptions);
  }

  updatetUser(editedUser: User): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/updateUser', editedUser, this.httpOptions);
  }

  updateUserRole(editedUser: User): Observable<UserRole> {
    this.getSession();
    return this.http.put<UserRole>(this.url + '/' + editedUser.id, editedUser, this.httpOptions);
  }

  deactivateUser(user: User): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/deactivateUser/', user, this.httpOptions);
  }

  activateUser(newUser: User): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/activateUser', newUser, this.httpOptions);
  }

  resetPassword(newUser: User): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/resetPassword', newUser, this.httpOptions);
  }

  changePassword(newUser: any): Observable<User> {
    this.getSession();
    return this.http.post<User>(this.url + '/changePassword', newUser, this.httpOptions);
  }

  logOut(): Observable<Session> {
    return this.http.post<Session>(this.baseUrl + '/auth/logout', this.session, this.httpOptions);
  }

  logIn(login: Login): Observable<Session> {
    this.httpOptions = {
      // headers: new Headers({
      headers: new HttpHeaders({
        'Content-Type':  'application/json'// ,
      // 'Authorization': 'my-auth-token'
      })
    };
    return this.http.post<Session>(this.baseUrl + '/auth/login', login, this.httpOptions);
  }
}
