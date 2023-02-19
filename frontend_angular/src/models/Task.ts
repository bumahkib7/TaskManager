export class Task {


	constructor(id: number | undefined,
	            title: string | undefined,
	            description: string | undefined,
	            priority: number | undefined,
	            user: User | undefined,
	            complete: Date | undefined,
	            project: Project | undefined,
	            created: Date | undefined,
	            version: number | undefined) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.user = user;
		this.complete = complete;
		this.project = project;
		this.created = created;
		this.version = version;
	}

	id: number | undefined;
	title: string | undefined;
	description: string | undefined;
	priority: number | undefined;
	user: User | undefined;
	complete: Date | undefined;
	// @ts-ignore
	project: Project | undefined;
	created: Date | undefined;
	version: number | undefined;
}


export class Project {

	constructor(id: number | undefined, name: string | undefined, description: string | undefined, user: User | undefined, created: Date | undefined, version: number | undefined) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.created = created;
		this.version = version;
	}
	id: number | undefined;
	name: string | undefined;
	description: string | undefined;
	user: User | undefined;
	created: Date | undefined;
	version: number | undefined;
}

export class User {

	constructor(id: number | undefined, name: string | undefined, email: string | undefined, password: string | undefined, created: Date | undefined, version: number | undefined) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.version = version;
	}
	id: number | undefined;
	name: string | undefined;
	email: string | undefined;
	password: string | undefined;
	created: Date | undefined;
	version: number | undefined;
}
