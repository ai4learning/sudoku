import React, {Component} from 'react'

export default class App extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='container'>{this.props.children || ''}</div>
    )
  }
}