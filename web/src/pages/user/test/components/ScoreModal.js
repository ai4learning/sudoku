import React, {Component} from 'react'
import {Modal, Button} from 'antd'

export default class ScoreModal extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <Modal
        title="测试成绩"
        visible={this.props.visible}
        closable={false}
        maskClosable={false}
        footer={null}
        onCancel={this.handleCancel.bind(this)}
        className='score_modal'
        >
        <p>本次测试<span className='score'>{this.props.score}</span>分！</p>
        <div className='actions'>
          <Button type='primary' className='btn-show-wrong' onClick={this.handleShowWrong.bind(this)}>查看错误</Button>
          <Button type='primary' className='btn-close-page' onClick={this.handleClosePage.bind(this)}>关闭页面</Button>
        </div>
      </Modal>
    )
  }

  handleCancel() {
    this.props.onCancel()
  }

  handleShowWrong() {
    this.props.onShowWrong()
  }

  handleClosePage() {
    this.props.onClosePage()
  }
}