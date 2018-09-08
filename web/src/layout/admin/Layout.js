import React, {Component} from 'react'

export default class Layout extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div>
        <div>admin</div>
        {this.props.children || ''}
      </div>
    )
  }
}