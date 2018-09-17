import React, { Component } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const NavWrapper = styled.div`
	box-sizing: border-box;
	background-image: linear-gradient(135deg, #97abff 10%, #123597 100%);
	color: #fefefe;
	display: flex;
	justify-content: space-between;
	align-items: center;
	@media (max-width: 1024px) {
		flex-direction: column;
	}
`;
const Brand = styled.div`
	padding: 7px 15px;
	display: flex;
	align-items: center;
	transition: all 300ms ease-in-out;
	&:hover {
		background-color: rgba(0, 0, 0, 0.1);
		cursor: pointer;
	}
`;

const BrandImage = styled.div`
	height: 50px;
	width: auto;
	font-size: 2.5rem;
`;

const StyledLink = styled(Link)`
	color: #fef;
	text-decoration: none;
	color: #fef;
	&:visited {
	}
`;

const Menu = styled.div`
	display: flex;
	@media (max-width: 900px) {
		flex-direction: column;
		padding-bottom: 10px;
	}
`;

const MenuItem = styled(StyledLink)`
	padding: 20px 10px;
	font-size: 1.3rem;
	height: 100%;
	font-weight: 600;
	letter-spacing: 1px;
	color: #fef;
	text-decoration: none;
	transition: all 200ms ease-in-out;
	&:hover {
		background-color: rgba(0, 0, 0, 0.1);
	}
	@media (max-width: 900px) {
		padding: 1px 10px;
		text-align: center;
	}
`;

export class Navbar extends Component {
	renderMenu() {
		switch (this.props.auth) {
			case null:
				return '';
			case false:
				return [
					<MenuItem key="1" to="/register">
						Register
					</MenuItem>,
					<MenuItem key="2" to="/login">
						Login
					</MenuItem>
				];
			case true:
				return [
					<MenuItem key="1" to="/questions">
						Questions
					</MenuItem>,
					<MenuItem key="2" to="/practice">
						Practice
					</MenuItem>,
					<MenuItem
						onClick={(event) => {
							document.cookie = 'token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
							window.location.reload();
						}}
						key="3"
						to="/logout">
						Logout
					</MenuItem>
				];
			default:
				return '';
		}
	}
	render() {
		return (
			<NavWrapper>
				<StyledLink to={this.props.auth ? 'exams' : '/'}>
					<Brand>
						<BrandImage>
							<span role="img" aria-label="Teacher Emoji">
								👨‍🏫👨‍🎓👨‍💻
							</span>
						</BrandImage>
					</Brand>
				</StyledLink>
				<Menu>{this.renderMenu()}</Menu>
			</NavWrapper>
		);
	}
}

export default Navbar;
