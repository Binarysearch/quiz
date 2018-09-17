import React, { Component } from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
	color: #efefef;
	font-size: 1.1rem;
	font-family: inherit;
	font-weight: 600;
	padding: 0.5rem 1.5rem;
	background: #123597;
	cursor: pointer;
	border-radius: 3px;
	border: none;
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1), 2px 2px 4px rgba(0, 0, 0, 0.1);
	transition: all 200ms ease-in-out;
	&:hover {
		background: #2a60bf;
		box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1), 3px 3px 5px rgba(0, 0, 0, 0.1);
	}
	&:active {
		background: #132d96;
		box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
	}
`;

export class Button extends Component {
	render() {
		return (
			<StyledButton onClick={this.props.onClick}>
				{this.props.buttonText}
			</StyledButton>
		);
	}
}

export default Button;
