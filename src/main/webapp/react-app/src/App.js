import React, { Component } from 'react';
import Navbar from './Components/Navbar';
import { BrowserRouter, Route, Redirect } from 'react-router-dom';
import { setCookie } from './Utils/setCookie';
import { getCookie } from './Utils/getCookie';

import Home from './Components/Home';
import RegisterForm from './Components/RegisterForm';
import LoginForm from './Components/LoginForm';
import Questions from './Components/Questions';
class App extends Component {
	constructor(props) {
		super(props);
		this.state = {
			auth: false,
			token: ''
		};
		this.updateSession = this.updateSession.bind(this);
	}
	componentWillMount() {
		const token = getCookie('token');
		if (token !== null) {
			this.setState({ auth: true, token });
		}
	}
	updateSession(token) {
		setCookie({ name: 'token', value: token, days: 30 });
		this.setState({ auth: true, token });
	}
	render() {
		return (
			<BrowserRouter>
				<div>
					<Navbar auth={this.state.auth} />
					<Route exact path="/" component={Home} />
					<Route path="/register" component={RegisterForm} />
					<Route
						path="/questions"
						render={(props) => {
							return this.state.auth ? <Questions /> : <Redirect to="/login" />;
						}}
					/>

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
