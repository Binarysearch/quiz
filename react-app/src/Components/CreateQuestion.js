import React, { Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';

import { StyledBox } from './Box';
import FormField from './FormField';
import Button from './Button';
import { updateField } from '../Utils/UpdateField';
import { getCookie } from '../Utils/getCookie';
const QuestionCreator = styled(StyledBox)`
	margin-top: 20px;
	width: 40%;
	@media (max-width: 1024px) {
		width: 90%;
	}
`;
const QuestionAnswers = styled.div`
	display: grid;
	grid-template-rows: 1fr 1fr;
	grid-template-columns: 1fr 1fr;
	grid-column-gap: 10px;
	grid-row-gap: 5px;
	@media (max-width: 768px) {
		grid-template-columns: 1fr;
	}
`;
const StyledTitle = styled.div`
	font-size: 1.2rem;
	font-weight: 600;
`;
const Answer = styled.div`
	display: flex;
	align-items: center;
`;

const AnswerButton = styled.div`
	padding: 5px 10px;
	display: flex;
	font-size: 1.2rem;
	align-items: center;
	cursor: pointer;
`;
const AnswerField = styled.input`
	width: 100%;
	box-sizing: border-box;
	height: 2.2rem;
	padding: 5px 10px;
	margin-top: 10px;
	font-size: 0.9rem;
	border: 1px solid ${(props) => (props.correct ? '#2ecc71' : '#dedede')};
	background-color: ${(props) => (props.correct ? '#2ecc714d' : '#fefefe')};

	border-radius: 3px;
	box-shadow: inset 2px 2px 3px rgba(0, 0, 0, 0.1);
`;
const CenteredDiv = styled.div`
	text-align: center;
	margin: 1rem;
`;
class CreateQuestion extends Component {
	constructor(props) {
		super(props);

		this.state = {
			correct: 0,
			question: '',
			answer1: '',
			answer2: '',
			answer3: '',
			answer4: ''
		};
		this.handleQuestionSubmit = this.handleQuestionSubmit.bind(this);
	}

	handleQuestionSubmit() {
		console.log(this.state);
		if (
			this.state.question !== '' &&
			this.state.answer1 !== '' &&
			this.state.answer2 !== '' &&
			this.state.correct !== 0
		) {
			axios
				.post(
					`https://binarysearch.es/quiz/crear_pregunta?pregunta=${
						this.state.question
					}&respuesta1=${this.state.answer1}&respuesta2=${
						this.state.answer2
					}&respuesta3=${this.state.answer3}&respuesta4=${
						this.state.answer4
					}&correcta=${this.state.correct}
				&token=${getCookie('token')}`
				)
				.then((response) => {
					window.location.reload();
				});
		}
	}

	updateField = updateField.bind(this);

	render() {
		return (
			<QuestionCreator>
				<FormField
					type="text"
					placeholder="Enunciado de la pregunta"
					name="question"
					labelText="Pregunta:"
					onChangeText={this.updateField}
				/>
				<StyledTitle>Respuestas:</StyledTitle>
				<QuestionAnswers>
					<Answer>
						<AnswerField
							correct={this.state.correct === 1 ? true : false}
							type="text"
							value={this.state.answer1}
							name="answer1"
							placeholder="Respuesta 1"
							onChange={(event) => {
								this.setState({
									answer1: event.target.value
								});
							}}
						/>
						<AnswerButton
							onClick={(event) => {
								this.setState({ correct: 1 });
							}}>
							<span role="img" aria-label="Correct button">
								{this.state.correct === 1 ? '✅' : '🚫'}
							</span>
						</AnswerButton>
					</Answer>
					<Answer>
						<AnswerField
							correct={this.state.correct === 2 ? true : false}
							type="text"
							value={this.state.answer2}
							placeholder="Respuesta 2"
							onChange={(event) => {
								this.setState({
									answer2: event.target.value
								});
							}}
						/>
						<AnswerButton
							onClick={(event) => {
								this.setState({ correct: 2 });
							}}>
							<span role="img" aria-label="Correct button">
								{this.state.correct === 2 ? '✅' : '🚫'}
							</span>
						</AnswerButton>
					</Answer>
					<Answer>
						<AnswerField
							correct={this.state.correct === 3 ? true : false}
							type="text"
							placeholder="Respuesta 3"
							value={this.state.answer3}
							onChange={(event) => {
								this.setState({
									answer3: event.target.value
								});
							}}
						/>
						<AnswerButton
							onClick={(event) => {
								this.setState({ correct: 3 });
							}}>
							<span role="img" aria-label="Correct button">
								{this.state.correct === 3 ? '✅' : '🚫'}
							</span>
						</AnswerButton>
					</Answer>
					<Answer>
						<AnswerField
							correct={this.state.correct === 4 ? true : false}
							type="text"
							placeholder="Respuesta 4"
							value={this.state.answer4}
							onChange={(event) => {
								this.setState({
									answer4: event.target.value
								});
							}}
						/>
						<AnswerButton
							onClick={(event) => {
								this.setState({ correct: 4 });
							}}>
							<span role="img" aria-label="Correct button">
								{this.state.correct === 4 ? '✅' : '🚫'}
							</span>
						</AnswerButton>
					</Answer>
				</QuestionAnswers>
				<CenteredDiv>
					<Button
						buttonText="Guardar pregunta"
						onClick={this.handleQuestionSubmit}
					/>
				</CenteredDiv>
			</QuestionCreator>
		);
	}
}

export default CreateQuestion;
