import React, { Component } from 'react';
import Navbar from './Components/Navbar';
import { BrowserRouter, Route } from 'react-router-dom';
import { setCookie } from './Utils/setCookie';

import Home from './Components/Home';
import RegisterForm from './Components/RegisterForm';
import LoginForm from './Components/LoginForm';
class App extends Component {
	constructor(props) {
		super(props);
		this.state = {
			auth: false
		};
	}
	updateSession(token) {
		setCookie('token', token, 30);
		this.setState({ auth: true });
	}
	render() {
		return (
			<BrowserRouter>
				<div>
					<Navbar auth={this.state.auth} />
					<Route exact path="/" component={Home} />
					<Route path="/register" component={RegisterForm} />
					<Route
						path="/login"
						render={() => <LoginForm updateSession={this.updateSession} />}
					/>
				</div>
			</BrowserRouter>
		);
	}
}

export default App;
