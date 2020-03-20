//package com.example.itext_pdf.Thymeleaf_PDF;
//
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.codec.Base64;
//import org.apache.batik.transcoder.TranscoderException;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.JPEGTranscoder;
//import org.apache.batik.transcoder.image.PNGTranscoder;
//import org.w3c.dom.Element;
//import org.xhtmlrenderer.extend.FSImage;
//import org.xhtmlrenderer.extend.ReplacedElement;
//import org.xhtmlrenderer.extend.ReplacedElementFactory;
//import org.xhtmlrenderer.extend.UserAgentCallback;
//import org.xhtmlrenderer.layout.LayoutContext;
//import org.xhtmlrenderer.pdf.ITextFSImage;
//import org.xhtmlrenderer.pdf.ITextImageElement;
//import org.xhtmlrenderer.render.BlockBox;
//import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//public class B64ImgReplacedElementFactory implements ReplacedElementFactory
//{
//
//	public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth, int cssHeight)
//	{
//		Element e = box.getElement();
//		if(e == null)
//		{
//			return null;
//		}
//		String nodeName = e.getNodeName();
//		if(nodeName.equals("img"))
//		{
//			String attribute = e.getAttribute("src");
//			FSImage fsImage;
//			try
//			{
//				fsImage = buildImage(attribute, uac);
//			}
//			catch(BadElementException e1)
//			{
//				fsImage = null;
//			}
//			catch(IOException e1)
//			{
//				fsImage = null;
//			}
//			if(fsImage != null)
//			{
//				if(cssWidth != -1 || cssHeight != -1)
//				{
//					fsImage.scale(cssWidth, cssHeight);
//				}
//				return new ITextImageElement(fsImage);
//			}
//		}
//
//		return null;
//	}
//
//	protected FSImage buildImage(String srcAttr, UserAgentCallback uac) throws IOException, BadElementException
//	{
//		if(srcAttr.startsWith("data:image/"))
//		{
//			// BASE64Decoder decoder = new BASE64Decoder();
//			// byte[] decodedBytes = decoder.decodeBuffer(b64encoded);
//			// byte[] decodedBytes = B64Decoder.decode(b64encoded);
//			byte[] decodedBytes = Base64.decode(srcAttr.substring(srcAttr.indexOf("base64,") + "base64,".length(), srcAttr.length()));
//			return new ITextFSImage(Image.getInstance(decodedBytes));
//		}
//		FSImage fsImage = uac.getImageResource(srcAttr).getImage();
//		if(fsImage == null)
//		{
//			return convertToPNG(srcAttr);
//		}
//		return null;
//	}
//
//	private FSImage convertToPNG(String srcAttr) throws IOException, BadElementException
//	{
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		PNGTranscoder t = new PNGTranscoder();
////		t.addTranscodingHint(JPEGTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER, (25.4f / 72f));
//		t.addTranscodingHint(JPEGTranscoder.KEY_WIDTH, 4000.0F);
//		t.addTranscodingHint(JPEGTranscoder.KEY_HEIGHT, 4000.0F);
//		try
//		{
//			t.transcode(
//					new TranscoderInput(srcAttr),
//					new TranscoderOutput(byteArrayOutputStream)
//			);
//		}
//		catch(TranscoderException e)
//		{
//			e.printStackTrace();
//		}
//		byteArrayOutputStream.flush();
//		byteArrayOutputStream.close();
//		return new ITextFSImage(Image.getInstance(byteArrayOutputStream.toByteArray()));
//	}
//
//	public void remove(Element e)
//	{
//	}
//
//	@Override
//	public void setFormSubmissionListener(FormSubmissionListener formSubmissionListener)
//	{
//
//	}
//
//	public void reset()
//	{
//	}
//}