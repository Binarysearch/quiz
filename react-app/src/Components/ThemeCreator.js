import React, { Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';

import Button from './Button';
import { getCookie } from '../Utils/getCookie';

const CreateTheme = styled.div`
	display: flex;
	align-items: center;
	min-width: 60px;
`;

const ThemeName = styled.input`
	width: 100%;
	box-sizing: border-box;
	height: 2.2rem;
	padding: 5px 10px;
	font-size: 0.9rem;
	border: 1px solid #dfdfdf;
	box-shadow: inset 2px 2px 3px rgba(0, 0, 0, 0.1);
	border-top-left-radius: 3px;
	border-bottom-left-radius: 3px;
`;

const ThemeConfirm = styled.span`
	background: #2ecc71;
	color: white;
	padding: 7px 10px;
	font-size: 0.9rem;
	cursor: pointer;
`;
const ThemeCancel = styled.span`
	background: #e74c3c;
	color: white;
	cursor: pointer;
	padding: 7px 10px;
	font-size: 0.9rem;
	border-top-right-radius: 3px;
	border-bottom-right-radius: 3px;
`;

export default class ThemeCreator extends Component {
	constructor(props) {
		super(props);

		this.state = {
			open: false,
			token: '',
			name: ''
		};
		this.handleOpen = this.handleOpen.bind(this);
		this.handleCreateTheme = this.handleCreateTheme.bind(this);
		this.handleChange = this.handleChange.bind(this);
	}
	componentWillMount() {
		this.setState({
			token: getCookie('token')
		});
	}
	handleOpen() {
		this.setState({
			open: !this.state.open
		});
	}
	handleCreateTheme() {
		if (this.state.name !== '') {
			axios
				.post(
					`https://binarysearch.es/quiz/crear_tema?nombre=${
						this.state.name
					}&token=${this.state.token}`
				)
				.then(() => {
					this.setState({ open: false });
					this.props.newTheme();
				});
		}
	}

	handleChange(event) {
		this.setState({ name: event.target.value });
	}

	render() {
		if (this.state.open) {
			return (
				<CreateTheme>
					<ThemeName
						type="text"
						placeholder="nombre"
						value={this.state.value}
						onChange={this.handleChange}
					/>
					<ThemeConfirm onClick={this.handleCreateTheme}>Confirm</ThemeConfirm>
					<ThemeCancel onClick={this.handleOpen}>Cancel</ThemeCancel>
				</CreateTheme>
			);
		} else {
			return (
				<div onClick={this.handleOpen}>
					<Button buttonText="Crear tema" />
				</div>
			);
		}
	}
}
