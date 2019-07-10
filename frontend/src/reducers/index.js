import { combineReducers } from 'redux';
import errors from './errors';
import projects from './projects';
import backlog from './backlog';

export default combineReducers({
  errors: errors,
  projects: projects,
  backlog: backlog
})