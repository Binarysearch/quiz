import React, { Component } from 'react';
import styled from 'styled-components';

export const StyledBox = styled.div`
	background-color: white;
	padding: 10px 30px;
	border: 1px solid #cdcdcd;
	border-radius: 5px;
	box-sizing: border-box;
	box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
	margin: 0 auto;
	margin-top: 50px;
`;

class Box extends Component {
	render() {
		return <StyledBox>{this.props.children}</StyledBox>;
	}
}

export { Box };
