import React, { Component } from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';


import ProjectItem from "./Project/ProjectItem";

export default class Dashboard extends Component {
  render() {
    return (
      <Container>
        <Row className="mt-4">
          <Col md={2}>
            <h2>Projects</h2>
          </Col>
          <Col md={{ span: 3, offset: 7 }}>
            <Button variant="primary" size="lg" href="#">
              Create New Project
            </Button>
          </Col>
        </Row>

        <Row className="mt-5">
          {
            [1, 2].map(n => (
              <Col className="mb-3" md={12} >
                <ProjectItem key={n}  /> 
              </Col>
            ))
          }
        </Row>
      </Container>
    );
  }
}
