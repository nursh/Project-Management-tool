import React, { Component } from 'react';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav';


class Header extends Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Navbar.Brand>Kanban Project Tool</Navbar.Brand>
        <Navbar.Toggle aria-controls="header" />
        <Navbar.Collapse id="header">
          <Nav className="mr-auto">
            <Nav.Link>Dashboard</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link>Sign Up</Nav.Link>
            <Nav.Link>Login</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    )
  }
}

export default Header;
