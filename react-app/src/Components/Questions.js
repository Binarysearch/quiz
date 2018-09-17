import React, { Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';

import SingleQuestion from './SingleQuestion';
import CreateQuestion from './CreateQuestion';
import Themes from './Themes';

const PageTitle = styled.h1`
	text-align: center;
	font-size: 2rem;
	color: #fefefe;
`;

class Questions extends Component {
	constructor(props) {
		super(props);

		this.state = {
			questions: []
		};
		this.renderQuestions = this.renderQuestions.bind(this);
	}
	componentWillMount() {
		axios
			.post(
				`https://binarysearch.es/quiz/get_preguntas?token=${this.props.token}`
			)
			.then((response) => {
				this.setState({ questions: response.data });
			});
	}

	renderQuestions() {
		return this.state.questions.map((question) => (
			<SingleQuestion key={question.id} question={question} />
		));
	}
	render() {
		return (
			<div>
				<PageTitle>Preguntas</PageTitle>
				<Themes token={this.props.token} />
				<CreateQuestion />
				{this.renderQuestions()}
			</div>
		);
	}
}

export default Questions;
