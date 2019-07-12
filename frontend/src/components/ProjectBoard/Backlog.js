import React, { Component } from "react";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import Container from "react-bootstrap/Container";
import Task from "./Tasks/Task";

class Backlog extends Component {
  render() {
    const { tasks } = this.props;
    const todoItems = tasks.filter(task => task.status === 'TO DO');
    const inProgressItems = tasks.filter(task => task.status === 'IN PROGRESS');
    const doneItems = tasks.filter(task => task.status === 'DONE');

    return (
      <Container>
        <Row className="mt-4">
          <Col md={4}>
            <Card bg="primary" text="white">
              <Card.Header as="h3">TO DO</Card.Header>
            </Card>
            {
              todoItems.map(task => <Task task={task} key={task.id} />)
            }
          </Col>

          <Col md={4}>
            <Card bg="secondary" text="white">
              <Card.Header as="h3">IN PROGRESS</Card.Header>
            </Card>
            {
              inProgressItems.map(task => <Task task={task} key={task.id} />)
            }
          </Col>

          <Col md={4}>
            <Card bg="success" text="white">
              <Card.Header as="h3">DONE</Card.Header>
            </Card>
            {
              doneItems.map(task => <Task task={task} key={task.id} />)
            }
          </Col>
        </Row>
      </Container>
    );
  }
}

export default Backlog;
