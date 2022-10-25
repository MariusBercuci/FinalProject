import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/shared/models/User';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  public user!: User;
  public profileImage!: File;

  public userCardForm!: FormGroup;

  constructor(private userService: UserService,
    //private authService: AuthenticationService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
  }

}
