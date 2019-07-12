import React, { Component } from "react";
import Button from "react-bootstrap/Button";
import Card from 'react-bootstrap/Card';


class Task extends Component {
  render() {
    const { id, task } = this.props;
    return (
      <div>
        <Card>
          <Card.Header>{task.projectSequence} -- Priority: {task.priority}</Card.Header>
          <Card.Body>
            <Card.Title>{task.summary}</Card.Title>
            <Card.Text>{task.acceptanceCriteria}</Card.Text>
          </Card.Body>
          <Card.Footer className="d-flex flex-row-reverse">
            <Button variant="danger">Delete</Button>
            <Button className="mr-4" href={`/updateTask/${id}`}>
              View / Update
            </Button>
          </Card.Footer>
        </Card>
      </div>
    );
  }
}

export default Task;
