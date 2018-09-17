import React, { Component } from 'react';
import styled from 'styled-components';

const Field = styled.div`
	margin: 10px 0px;
`;
const FieldLabel = styled.label`
	font-size: 1.2rem;
	font-weight: 700;
`;

const FieldInput = styled.input`
	width: 100%;
	box-sizing: border-box;
	height: 2.2rem;
	padding: 5px 10px;
	margin-top: 10px;
	font-size: 0.9rem;
	border: 1px solid #dfdfdf;
	border-radius: 3px;
	box-shadow: inset 2px 2px 3px rgba(0, 0, 0, 0.1);
`;

export class FormField extends Component {
	render() {
		const { labelText, placeholder, type, name, onChangeText } = this.props;
		return (
			<Field onClick={this.props.onClick}>
				<FieldLabel>{labelText}</FieldLabel>
				<FieldInput
					type={type}
					placeholder={placeholder}
					name={name}
					onChange={(event) => {
						onChangeText({ name, value: event.target.value });
					}}
				/>
			</Field>
		);
	}
}

export default FormField;
