import React from 'react';
import {render} from 'react-dom';



class Task extends React.Component {
  render() {
    return (
        <div>
          This is task area
        </div>
    );
  }
}

render(<Task/>, document.getElementById('app'));
