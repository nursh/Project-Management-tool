import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';


export default class ProjectBoard extends Component {


  render() {
    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <Button size="lg">Create task</Button>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={4}>
            <Card>
              <Card.Header as="h3">TO DO</Card.Header>
            </Card>

            <Card>
              <Card.Header>ID: sequence -- Priority: priority</Card.Header>
              <Card.Body>
                <Card.Title>Task summary</Card.Title>
                <Card.Text>Acceptance Criteria</Card.Text>
 
              </Card.Body>
              <Card.Footer className="d-flex flex-row-reverse">
                <Button variant="danger">Delete</Button>
                <Button className="mr-4">View / Update</Button>
              </Card.Footer>
            </Card>
          </Col>

          <Col md={4}>
            <Card>
              <Card.Header as="h3">IN PROGRESS</Card.Header>
            </Card>
          </Col>

          <Col md={4}>
            <Card>
              <Card.Header as="h3">DONE</Card.Header>
            </Card>
          </Col>
        </Row>
      </Container>
    );
  }
}