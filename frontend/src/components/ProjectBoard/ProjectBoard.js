import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';


class ProjectBoard extends Component {


  render() {
    const { id } = this.props.match.params;

    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <Button size="lg" href={`/addTask/${id}`}>Create task</Button>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={4}>
            <Card bg="primary" text="white">
              <Card.Header as="h3">TO DO</Card.Header>
            </Card>

            <Card>
              <Card.Header>ID: {`${id}`} -- Priority: priority</Card.Header>
              <Card.Body>
                <Card.Title>Task summary</Card.Title>
                <Card.Text>Acceptance Criteria</Card.Text>
 
              </Card.Body>
              <Card.Footer className="d-flex flex-row-reverse">
                <Button variant="danger">Delete</Button>
                <Button className="mr-4" href={`/updateTask/${id}`}>View / Update</Button>
              </Card.Footer>
            </Card>
          </Col>

          <Col md={4}>
            <Card bg="secondary" text="white">
              <Card.Header as="h3">IN PROGRESS</Card.Header>
            </Card>
          </Col>

          <Col md={4}>
            <Card bg="success" text="white">
              <Card.Header as="h3">DONE</Card.Header>
            </Card>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default ProjectBoard;