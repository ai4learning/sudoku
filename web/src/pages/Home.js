import React, {Component} from 'react'

export default class Home extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div></div>
    )
  }

  componentWillMount() {
    this.props.router.push('/user')
  }
}