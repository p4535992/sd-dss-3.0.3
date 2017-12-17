/*
 * DSS - Digital Signature Services
 *
 * Copyright (C) 2011 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
 *
 * Developed by: 2011 ARHS Developments S.A. (rue Nicolas Bové 2B, L-1253 Luxembourg) http://www.arhs-developments.com
 *
 * This file is part of the "DSS - Digital Signature Services" project.
 *
 * "DSS - Digital Signature Services" is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * DSS is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * "DSS - Digital Signature Services".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.europa.ec.markt.dss.applet.service;

import eu.europa.ec.markt.dss.exception.EncodingException;
import eu.europa.ec.markt.dss.exception.EncodingException.MSG;
import eu.europa.ec.markt.dss.applet.shared.OCSPRequestMessage;
import eu.europa.ec.markt.dss.applet.shared.OCSPResponseMessage;
import eu.europa.ec.markt.dss.validation.ocsp.OCSPSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.ocsp.BasicOCSPResp;

/**
 * Retrieve an OCSPResp from the wrapped OCSPSource.
 * 
 *
 * @version $Revision: 2823 $ - $Date: 2013-10-29 20:39:42 +0100 (mar., 29 oct. 2013) $
 */

public class OCSPRequestHandler extends AbstractServiceHandler<OCSPRequestMessage, OCSPResponseMessage> {

    private static final Logger LOG = Logger.getLogger(OCSPRequestHandler.class.getName());
    
    private OCSPSource ocspSource;

    /**
     * @param ocspSource the ocspSource to set
     */
    public void setOcspSource(OCSPSource ocspSource) {
        this.ocspSource = ocspSource;
    }

    @Override
    protected OCSPResponseMessage handleRequest(OCSPRequestMessage message) throws IOException {

        try {
            CertificateFactory factory = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(message
                    .getCertificate()));
            X509Certificate issuerCert = (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(
                    message.getIssuerCert()));

            BasicOCSPResp ocsp = ocspSource.getOCSPResponse(cert, issuerCert);

            OCSPResponseMessage response = new OCSPResponseMessage();
            if (ocsp != null) {
                response.setOcspResponse(ocsp.getEncoded());
            }

            return response;
        } catch (CertificateException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new EncodingException(MSG.CERTIFICATE_CANNOT_BE_READ);
        }

    }

}