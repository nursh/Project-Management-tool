import React, { Component } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { connect } from "react-redux";
import { deleteTask } from "../../../actions/backlog";
import PropTypes from "prop-types";

class Task extends Component {
  onDelete = (backlogId, taskId) => {
    this.props.deleteTask(backlogId, taskId);
  };

  render() {
    const { task } = this.props;
    return (
      <div>
        <Card>
          <Card.Header>
            {task.projectSequence} -- Priority: {task.priority}
          </Card.Header>
          <Card.Body>
            <Card.Title>{task.summary}</Card.Title>
            <Card.Text>{task.acceptanceCriteria}</Card.Text>
          </Card.Body>
          <Card.Footer className="d-flex flex-row-reverse">
            <Button
              variant="danger"
              onClick={() => this.onDelete(task.projectIdentifier, task.projectSequence)}
            >
              Delete
            </Button>
            <Button
              className="mr-4"
              href={`/updateTask/${task.projectIdentifier}/tasks/${
                task.projectSequence
              }`}
            >
              View / Update
            </Button>
          </Card.Footer>
        </Card>
      </div>
    );
  }
}

Task.propTypes = {
  deleteTask: PropTypes.func.isRequired
};

export default connect(
  null,
  { deleteTask }
)(Task);
