import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';
import Backlog from './Backlog';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getBacklog } from '../../actions/backlog';


class ProjectBoard extends Component {

  state = {
    errors: {}
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }

  render() {
    const { id } = this.props.match.params;
    const { tasks } = this.props.backlog;
    const { errors } = this.state;
    let BoardContent;
    const boardAlgorithm = (errors, tasks) => {
      if (tasks.length < 1 && errors.projectNotFound) {
        return (
          <Alert variant='danger' className="mt-5">
            {errors.projectNotFound}
          </Alert>
        );
      } else if (tasks.length < 1) {
        return (
          <Alert variant='info' className="mt-5">
            There are no tasks in the project.
          </Alert>
        );
      }
      return <Backlog id={id} tasks={tasks} />;
    }
    BoardContent = boardAlgorithm(errors, tasks);
    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <Button size="lg" href={`/addTask/${id}`}>Create task</Button>
          </Col>
        </Row>

        {BoardContent}
      </Container>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({ ...state });

export default connect(
  mapStateToProps,
  { getBacklog }
)(ProjectBoard);