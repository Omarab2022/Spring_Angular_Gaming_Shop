import { Injectable } from '@angular/core';


const TOKEN ='ecom-token';

const USER ='ecom-user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }


  public saveToken(token : string):void{

    window.localStorage.removeItem(TOKEN); //REMOVE the old token

    window.localStorage.setItem(TOKEN,token); //set the new token

  }


  public saveUser(user : any):void{

    window.localStorage.removeItem(USER); //REMOVE the existing user

    window.localStorage.setItem(USER,JSON.stringify(user)); //set the new user
    //JSON.stringify is used to convert the user object to a JSON string before storing it in local storage

  }


  static getToken() :string{

    return localStorage.getItem(TOKEN);

  }

  static getUser() :any{
    return JSON.parse(localStorage.getItem(USER));
  }


  static getUserId():string{

    const user = this.getUser();
    if (user == null) {
      return '';
    }

    return user.userId ;

  }


  static getUserRole():string{

    const user = this.getUser();
    if (user == null) {
      return "";
    }

    return user.role ;

  }


  static isAdminLoggedIn():boolean{
    if (this.getToken === null) {

      return false ;
      
    }
    const role : string = this.getUserRole() ;

    return role == 'ADMIN' ;
  }

  static isCustomerLoggedIn():boolean{
    if (this.getToken === null) {

      return false ;
      
    }
    const role : string = this.getUserRole() ;

    return role == 'CUSTUMER' ;
  }


  static signOut() :void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
