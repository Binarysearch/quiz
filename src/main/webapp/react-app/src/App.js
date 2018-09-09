import React from 'react';
import Navbar from './Components/Navbar';
import { BrowserRouter, Route } from 'react-router-dom';

import Home from './Components/Home';
import RegisterForm from './Components/RegisterForm';
const App = () => (
	<BrowserRouter>
		<div>
			<Navbar auth={true} />
			<Route exact path="/" component={Home} />
			<Route path="/register" component={RegisterForm} />
		</div>
	</BrowserRouter>
);

export default App;
