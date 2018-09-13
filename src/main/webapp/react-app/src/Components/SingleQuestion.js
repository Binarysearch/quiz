import React, { Component } from 'react';
import { StyledBox } from './Box';
import styled from 'styled-components';

const QuestionTitle = styled.h1`
	text-align: center;
	font-size: 1.5rem;
`;

const QuestionBox = styled(StyledBox)`
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
const SingleAnswer = styled.div`
	padding: 5px 15px;
	border: 2px solid ${(props) => (props.correct ? '#2ecc71' : '#dedede')};
	border-radius: 3px;
	background-color: ${(props) => (props.correct ? '#2ecc714d' : '#fefefe')};
	color: ${(props) =>
		props.correct ? 'rgba(0, 0, 0, 0.5)' : 'rgba(0, 0, 0, 0.8)'};
`;

class SingleQuestion extends Component {
	render() {
		console.log(this.props);
		const {
			pregunta,
			respuesta1,
			respuesta2,
			respuesta3,
			respuesta4,
			correcta
		} = this.props.question;
		return (
			<QuestionBox>
				<QuestionTitle>{pregunta}</QuestionTitle>
				<QuestionAnswers>
					<SingleAnswer correct={correcta === 1 ? true : false}>
						{respuesta1 ? respuesta1 : ' '}
					</SingleAnswer>
					<SingleAnswer correct={correcta === 2 ? true : false}>
						{respuesta2 ? respuesta2 : ' '}
					</SingleAnswer>
					<SingleAnswer correct={correcta === 3 ? true : false}>
						{respuesta3 ? respuesta3 : ' '}
					</SingleAnswer>
					<SingleAnswer correct={correcta === 4 ? true : false}>
						{respuesta4 ? respuesta4 : ' '}
					</SingleAnswer>
				</QuestionAnswers>
			</QuestionBox>
		);
	}
}

export default SingleQuestion;
