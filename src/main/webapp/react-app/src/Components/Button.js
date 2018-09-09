import React, { Component } from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
	color: #efefef;
	font-size: 1.1rem;
	font-weight: 600;
	margin: 1rem;
	padding: 0.5rem 1.5rem;
	background-image: linear-gradient(135deg, #ff96f9 10%, #c32bac 100%);
	border-radius: 3px;
	border: none;
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
