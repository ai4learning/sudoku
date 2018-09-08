package com.goldfish.utils;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导入导出
 * 
 * @author wyl
 */
public class ExcelUtil {
	private final static Log logger = LogFactory.getLog(ExcelUtil.class);

	private static final ExcelUtil instance = new ExcelUtil();

	private ExcelUtil() {
	}

	public static ExcelUtil getInstance() {
		return instance;
	}

	/**
	 * 下载模板
	 * 
	 * @return
	 */
	@RequestMapping("/down")
	public void down(String fileName, HttpServletRequest request, HttpServletResponse response) {
		InputStream is = null;
		OutputStream os = null;
		ByteArrayOutputStream baos = null;
		File file = null;
		try {
			file = new File(request.getSession().getServletContext().getRealPath("/") + "/excel/" + fileName);
			String userAgent = request.getHeader("User-Agent");
			fileName = URLEncoder.encode(fileName, "UTF8");
			String downloadName = DateUtils.format(new Date(),"yyyyMMddHHmm") + "_" + fileName;
			// 默认使用IE的方式进行编码
			String rtn = "filename=\"" + downloadName + "\"";
			rtn = compRtn(downloadName, userAgent, rtn);
			// 设置response的头信息
			response.setHeader("Content-disposition", "attachment;" + rtn);
			is = new BufferedInputStream(new FileInputStream(file));
			// 定义输出字节流
			baos = new ByteArrayOutputStream();
			// 定义response的输出流
			os = new BufferedOutputStream(response.getOutputStream());
			// 定义buffer
			byte[] buffer = new byte[4 * 1024]; // 4k Buffer
			int read = 0;
			// 从文件中读入数据并写到输出字节流中
			while ((read = is.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
			// 将输出字节流写到response的输出流中
			os.write(baos.toByteArray());

		}
		catch (FileNotFoundException e) {
			logger.error("ExcelUtil -> down error! FileNotFoundException !", e);
		}
		catch (IOException e) {
			logger.error("ExcelUtil -> down error! IOException !", e);
		}
		catch (Exception e) {
			logger.error("ExcelUtil -> down error!", e);
		}
		finally {
			IOUtils.closeQuietly(baos);
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * 导出数据
	 * 
	 * @param list
	 * @param fileName
	 * @param request
	 * @param response
	 */
	public void exportData(@SuppressWarnings("rawtypes") List list, Map<String,Object> infoMap, String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> dataMap = null;
		if (infoMap != null) {
			dataMap=infoMap;
		}else{
			dataMap=new HashMap<String, Object>();
		}
		dataMap.put("list", list);

		// 默认使用IE的方式进行编码
		try {
			String userAgent = request.getHeader("User-Agent");
			fileName = URLEncoder.encode(fileName, "UTF8");
			String downloadName = DateUtils.format(new Date(),"yyyyMMddHHmm") + "_" + fileName;
			String rtn = "filename=\"" + downloadName + "\"";
			rtn = compRtn(downloadName, userAgent, rtn);
			this.exportExcel(request, response, rtn, fileName, dataMap);
		}
		catch (Exception e) {
			logger.error("ExcelUtil exportExcel error !", e);
		}
	}

	/**
	 * 组装头部信息
	 * 
	 * @param fileName
	 * @param userAgent
	 * @param rtn
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String compRtn(String fileName, String userAgent, String rtn) throws UnsupportedEncodingException {
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				rtn = "filename=\"" + fileName + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				rtn = "filename*=UTF-8''" + fileName;
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			// else if (userAgent.indexOf("applewebkit") != -1) {
			// fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
			// rtn = "filename=\"" + fileName + "\"";
			// }
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				rtn = "filename*=UTF-8''" + fileName;
			}
		}
		return rtn;
	}

	/**
	 * @param request
	 * @param response
	 * @param exportName
	 *            ：生成下载的文件名
	 * @param templateName
	 *            ：位于指定的路径下的模板文件名
	 * @param dataMap
	 *            ：需要导出的数据
	 * @throws Exception
	 */
	private void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportName,
			String templateName, Map<String, Object> dataMap) throws Exception {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;" + exportName);

		String templateFullPath = request.getSession().getServletContext().getRealPath("/") + "excel/" + templateName;
		XLSTransformer transformer = new XLSTransformer();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(templateFullPath));
			Workbook workbook = transformer.transformXLS(is, dataMap);
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		}
		catch (Exception e) {
			logger.error("ExcelUtil exportExcel error !", e);
		}
		finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
		}
	}

}
