import { combineReducers } from 'redux';
import errors from './errors';
import projects from './projects';

export default combineReducers({
  errors: errors,
  projects: projects
})