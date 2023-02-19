import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Task} from '../models/Task';

@Injectable({
	providedIn: 'root'
})
export class TaskService {

	private apiUrl = 'http://localhost:8080/api/tasks';

	constructor(private http: HttpClient) {
	}

	getAllTasks(): Observable<Task[]> {
		return this.http.get<Task[]>(this.apiUrl);
	}

	createTask(task: { description: any; title: any; priority: any }): Observable<Task> {
		return this.http.post<Task>(this.apiUrl, task);
	}

	updateTask(task: Task): Observable<Task> {
		const url = `${this.apiUrl}/${task.id}`;
		return this.http.put<Task>(url, task);
	}

	deleteTask(task: Task): Observable<Task> {
		const url = `${this.apiUrl}/${task.id}`;
		return this.http.delete<Task>(url);
	}
}

