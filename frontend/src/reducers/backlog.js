import { 
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK
} from '../actions/types';

const initialState = {
  tasks: [],
  task: {}
};

export default function(state = initialState, action) {
  switch(action.type) {

    case GET_BACKLOG: 
      return {
        ...state,
        tasks: action.payload
      }

    case GET_PROJECT_TASK: 
      return {
        ...state,
        task: action.payload
      }

    case DELETE_PROJECT_TASK:
      return {
        ...state
        // TO_DO
      }
    default: 
      return state;
  }
}