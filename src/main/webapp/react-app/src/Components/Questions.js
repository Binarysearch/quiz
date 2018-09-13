import React, { Component } from 'react';

import SingleQuestion from './SingleQuestion';

class Questions extends Component {
	constructor(props) {
		super(props);

		this.state = {
			questions: [
				{
					id: 3,
					tema: 0,
					pregunta: '¿Cuál es la fórmula quimica de la sal?',
					respuesta1: 'NaCl',
					respuesta2: 'ClNa',
					respuesta3: 'SAl',
					respuesta4: 'THC',
					correcta: 1
				},
				{
					id: 3,
					tema: 0,
					pregunta: '¿De que color es el caballo blanco de Santiago?',
					respuesta1: 'Plata',
					respuesta2: 'Blanco',
					respuesta3: 'Oro',
					correcta: 2
				}
			]
		};
		this.renderQuestions = this.renderQuestions.bind(this);
	}
	renderQuestions() {
		return this.state.questions.map((question) => (
			<SingleQuestion key={question.id} question={question} />
		));
	}
	render() {
		return <div>{this.renderQuestions()}</div>;
	}
}

export default Questions;
