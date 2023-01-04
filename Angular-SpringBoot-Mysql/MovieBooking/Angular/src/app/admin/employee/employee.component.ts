import { Component, Inject, Input, OnInit, ViewChild } from '@angular/core';
import { NewEmployee, UpdateProfile, User } from 'src/app/model/model';
import { ServiceService } from 'src/app/service.service';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class EmployeeComponent implements OnInit {
  @ViewChild('closebutton') closebutton: any;
  constructor(
    private service: ServiceService,
    @Inject(DOCUMENT) private document: Document
  ) {}
  settingpage: string = 'all-emp';
  insideProfile: string = 'myactivity';
  //editform
  @Input() role: string | undefined;
  @Input() code: number = 0;
  @Input() changePass: boolean | undefined;

  user: User = new User();
  employee: NewEmployee = new NewEmployee();
  profile: UpdateProfile = new UpdateProfile();
  allDetails: boolean = false;
  loading:boolean=true;
  //all-emp
  // oneview
  ngOnInit(): void {
    console.log(this.role + '1888');

    this.preValidate();
  }

  preValidate() {
    this.loading=true
    if (this.changePass) {
      console.log('Change pass page');
      this.viewPerticularEmployee(this.code);
      this.insideProfile = 'updatePass';
    } else {
      this.loadAllEmployee();
    }

  }

  public popUpCloce() {
    this.closebutton.nativeElement.click();
  }

  roleEmp: string = '';
  addEmployee() {
    this.employee.user = this.user;
    console.log(this.employee);
    console.log(this.roleEmp);
    this.service.addEmployee(this.roleEmp, this.employee).subscribe((data) => {
      alert(data.message);
    });
    this.loadAllEmployee();
    this.popUpCloce();
  }
  onChangeform(a: string) {
    console.log('HIIIIIII');
    console.log(this.profile.password);
    if (
      this.profile.password !== '' ||
      this.profile.password !== undefined ||
      this.profile.mobileNo !== '' ||
      this.profile.mobileNo !== undefined ||
      this.profile.email !== '' ||
      this.profile.email !== undefined ||
      this.profile.fullName !== '' ||
      this.profile.fullName !== undefined ||
      this.profile.email !== '' ||
      this.profile.email !== undefined
    ) {
      this.allDetails = false;
      console.log('HIIIIIII');
    } else {
      this.allDetails = true;
    }
  }

  onBlock() {
    this.service.blockAdmin(this.oneEmployee.workerCode).subscribe((data) => {
      alert(data.message);
    });
    this.oneEmployee.accountStatus = 'BLOCKED';
  }
  onActivate() {
    this.service.activate(this.oneEmployee.workerCode).subscribe((data) => {
      alert(data.message);
    });
    this.oneEmployee.accountStatus = 'ACTIVE';
  }
  onDelete() {
    this.service
      .deleteEmployee(this.oneEmployee.workerCode)
      .subscribe((data) => {
        alert(data.message);
      });

    this.settingpage = 'all-emp';
    this.insideProfile = 'insideProfile';
  }
  oneEmployee: any = {
    workerCode: 1017,
    mobileNo: null,
    fullName: 'Ananth',
    doorNo: '456/AC',
    area: 'Chennai',
    salary: 18000,
    author: null,
    firstPass: null,
    fromdate: '2022-12-26',
    accountStatus: 'YETTOACTIVATE',
    user: {
      id: 10,
      username: 'Admin1',
      email: 'admin1@gmail.com',
      password: null,
      roles: [
        {
          id: 2,
          name: 'ROLE_ADMIN',
        },
      ],
    },
  };
  employees: any[] = [
    {
      workerCode: 1016,
      fullName: 'SANTHIYA',
      doorNo: '456/AC',
      area: 'Chennai',
      salary: 0,
      mobileNo: '9600929071',
      role: 'ROLE_MANAGER',
      fromdate: '2022-12-25',
      accountStatus: 'ACTIVE',
    },
    {
      workerCode: 1017,
      fullName: 'Ananth',
      doorNo: '456/AC',
      area: 'Chennai',
      salary: 0,
      mobileNo: null,
      role: 'ROLE_ADMIN',
      fromdate: '2022-12-26',
      accountStatus: 'YETTOACTIVATE',
    },
  ];

  loadAllEmployee() {

    this.employees = [];
    this.service.getAllEmployee().subscribe((data) => {
      this.employees = data;
    });
    this.loading=false
    console.log(this.employees);
  }

  viewPerticularEmployee(a: number) {
    this.oneEmployee = {};
    this.service.getOneEmployee(a).subscribe((data) => {
      this.oneEmployee = data;
    });
    this.settingpage = 'oneview';
  }

  viewEmployee(a: number) {
    if (this.role === 'ROLE_ADMIN') {
      console.log(a + ' ' + this.code);
      if (this.code === a) {
        this.viewPerticularEmployee(a);
      } else {
        console.log('you cant not view');
      }
    } else {
      this.viewPerticularEmployee(a);
    }
  }

  passWord(): boolean {
    if (this.profile.password != '') {
      return true;
    } else {
      return false;
    }
  }

  passWordForm() {
    this.profile.password = '';
    this.insideProfile = 'updatePass';
  }
  Update(a: string) {
    if (a == 'profile') {
      this.service
        .updateProfile(this.oneEmployee.workerCode, this.profile)
        .subscribe((data) => {
          if (data.message === 'Wrong Password') {
            alert('Wrong Password....');
          }
          if (data.message === 'Successfully Updated') {
            alert('Profile Updated....');
            this.viewPerticularEmployee(this.oneEmployee.workerCode);
            this.insideProfile = 'myactivity';
          }
        });
    }

    if (a == 'password') {
      this.service
        .updatePassword(this.oneEmployee.workerCode, this.profile)
        .subscribe((data) => {
          if (data.message === 'You Entered Old Password...') {
            alert('You Entered Old Password...');
          }
          if (data.message === 'Password Updated') {
            alert('Password Updated');
            this.viewPerticularEmployee(this.oneEmployee.workerCode);
            this.insideProfile = 'myactivity';
          }
        });
    }
  }

  acticity() {
    this.insideProfile = 'myactivity';
  }
  editForm() {
    this.profile.email = this.oneEmployee.user.email;
    this.profile.fullName = this.oneEmployee.fullName;
    this.profile.username = this.oneEmployee.user.username;
    this.profile.mobileNo = this.oneEmployee.mobileNo;
    this.profile.password = '';
    console.log(this.profile);
    this.insideProfile = 'editform';
  }

  roleChanger(a: string) {
    console.log(a);
    let role = '';
    if (a === 'ROLE_ADMIN') {
      role = 'ROLE_MANAGER';
    }
    if (a === 'ROLE_MANAGER') {
      role = 'ROLE_ADMIN';
    }
    console.log(role);

    this.service
      .roleChange(this.oneEmployee.workerCode, role)
      .subscribe((data) => {
        alert(data.message);
      });

    this.viewPerticularEmployee(this.oneEmployee.workerCode);
  }
}
