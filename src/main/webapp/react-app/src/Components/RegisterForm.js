import React, { Component } from 'react';
import styled from 'styled-components';

import { updateField } from '../Utils/UpdateField';
import FormField from './FormField';
import Button from './Button';

const StyledForm = styled.form`
	background-color: white;
	padding: 10px 30px;
	border: 1px solid #cdcdcd;
	border-radius: 5px;
	box-sizing: border-box;
	box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
	width: 30%;
	margin: 0 auto;
	margin-top: 50px;
	@media (max-width: 1200px) {
		width: 60%;
	}
	@media (max-width: 768px) {
		width: 90%;
	}
`;

const FormTitle = styled.h1`
	text-align: center;
	font-size: 2rem;
	font-weight: 700;
`;
const CenteredDiv = styled.div`
	text-align: center;
`;

export class RegisterForm extends Component {
	constructor(props) {
		super(props);

		this.state = {
			email: '',
			password: ''
		};
	}

	updateField = updateField.bind(this);

	render() {
		return (
			<StyledForm>
				<FormTitle>Create Accout</FormTitle>
				<FormField
					value={this.state.email}
					type="email"
					name="email"
					placeholder="Email"
					labelText="Email:"
					onChangeText={this.updateField}
				/>
				<FormField
					value={this.state.password}
					type="password"
					name="password"
					placeholder="Password"
					labelText="Password:"
					onChangeText={this.updateField}
				/>
				<CenteredDiv>
					<Button
						buttonText="Register"
						onClick={(event) => {
							event.preventDefault();
							console.log(this.state);
						}}
					/>
				</CenteredDiv>
			</StyledForm>
		);
	}
}

export default RegisterForm;