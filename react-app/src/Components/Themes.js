import React, { Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';

import ThemeCreator from './ThemeCreator';

const ThemeContainer = styled.div`
	margin: 0 auto;
	display: flex;
	align-items: center;
	margin-top: 20px;
	width: 60%;
	@media (max-width: 1024px) {
		width: 90%;
	}
`;

const SingleTheme = styled.div`
	padding: 5px 15px;
	font-size: 1.2rem;
	font-weight: 400;
	color: rgba(255, 255, 255, 0.9);
	background: #3498db;
	border-radius: 5px;
	margin: 5px;
`;

export class Themes extends Component {
	constructor(props) {
		super(props);

		this.state = {
			themes: []
		};
		this.renderThemes = this.renderThemes.bind(this);
		this.newTheme = this.newTheme.bind(this);
	}
	componentWillMount() {
		axios
			.post(`https://binarysearch.es/quiz/get_temas?token=${this.props.token}`)
			.then((response) => {
				console.log(response);
				this.setState({ themes: response.data });
			});
	}

	renderThemes() {
		return this.state.themes.map((theme) => (
			<SingleTheme key={theme.id}>{theme.nombre}</SingleTheme>
		));
	}

	newTheme() {
		axios
			.post(`https://binarysearch.es/quiz/get_temas?token=${this.props.token}`)
			.then((response) => {
				console.log(response);
				this.setState({ themes: response.data });
			});
	}

	render() {
		return (
			<ThemeContainer>
				{this.renderThemes()}
				<ThemeCreator
					newTheme={() => {
						this.newTheme();
					}}
				/>
			</ThemeContainer>
		);
	}
}

export default Themes;
