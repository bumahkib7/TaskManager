import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {Project, Task, User} from '../../../models/Task';
import {ProjectService} from '../../project.service';
import {TaskService} from '../../../services/taskservice.service';
import {UserService} from '../../user.service';


@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent implements OnInit {
  taskForm: FormGroup;

  users: User[];
  projects: Project[];
  priorities: number[];

  constructor(
    private formBuilder: FormBuilder,

    private FormControl: FormControl,
    private taskService: TaskService,
    private userService: UserService,
    private projectService: ProjectService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.taskForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.maxLength(1000)],
      priority: [1, Validators.required],
      user: ['', Validators.required],
      project: ['', Validators.required],
      complete: [new Date(), Validators.required],
    });
    this.users = [];
    this.projects = [];
    this.priorities = [1, 2, 3, 4, 5];
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      (users) => {
        this.users = users;
      },
      (error) => {
        this.snackBar.open('Failed to load users', 'Close');
      }
    );

    this.projectService.getProjects().subscribe(
      (projects) => {
        this.projects = projects;
      },
      (error) => {
        this.snackBar.open('Failed to load projects', 'Close');
      }
    );
  }

  onSubmit(): void {
    const task: Task = new Task(
      undefined,
      this.taskForm.controls['title'].value,
      this.taskForm.controls['description'].value,
      this.taskForm.controls['priority'].value,
      this.taskForm.controls['user'].value,
      this.taskForm.controls['complete'].value,
      this.taskForm.controls['project'].value,
      undefined,
      undefined
    );

    this.taskService.createTask(task).subscribe(
      (createdTask) => {
        this.snackBar.open('Task created', 'Close');
        this.router.navigate(['/tasks']);
      },
      (error) => {
        this.snackBar.open('Failed to create task', 'Close');
      }
    );
  }
}
