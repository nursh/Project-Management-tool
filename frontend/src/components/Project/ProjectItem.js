import React, { Component } from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import { FaTrashAlt, FaEdit, FaTasks } from "react-icons/fa";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { deleteProject } from "../../actions";

class ProjectItem extends Component {
  onDeleteClick = id => {
    this.props.deleteProject(id);
  };

  render() {
    const { project } = this.props;
    return (
      <Card>
        <Card.Header>{project.identifier}</Card.Header>
        <Card.Body>
          <Card.Title>{project.name}</Card.Title>
          <Card.Text>{project.description}</Card.Text>
        </Card.Body>
        <Card.Footer className="d-flex flex-row-reverse">
          <Button
            variant="outline-danger"
            onClick={() => this.onDeleteClick(project.identifier)}
            className="d-flex align-items-center  ml-3"
          >
            <FaTrashAlt />
            <span className="pl-2">Delete project</span>
          </Button>
          <Button
            variant="outline-info"
            href={`/editProject/${project.identifier}`}
            className="d-flex align-items-center  ml-3"
          >
            <FaEdit />
            <span className="pl-2">Edit project</span>
          </Button>
          <Button
            variant="outline-primary"
            href={`/projectBoard/${project.identifier}`}
            className="d-flex align-items-center  ml-3"
          >
            <FaTasks />
            <span className="pl-2">View project</span>
          </Button>
        </Card.Footer>
      </Card>
    );
  }
}

ProjectItem.propTypes = {
  deleteProject: PropTypes.func.isRequired
};

export default connect(
  null,
  { deleteProject }
)(ProjectItem);
